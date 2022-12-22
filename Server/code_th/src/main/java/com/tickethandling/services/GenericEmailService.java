package com.tickethandling.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tickethandling.auth.dto.UserDetailsDTO;
import com.tickethandling.auth.repositories.DepartmentsRepository;
import com.tickethandling.auth.repositories.OrganizationsRepository;
import com.tickethandling.auth.repositories.UserRepository;
import com.tickethandling.dto.DepartmentsDTO;
import com.tickethandling.dto.OrganizationsDTO;
import com.tickethandling.dto.TicketDTO;
import com.tickethandling.dto.TicketMetaDataDTO;
import com.tickethandling.email.EmailService;
import com.tickethandling.repositories.TicketMetaDataRepository;
import com.tickethandling.repositories.TicketRepository;

@Service
public class GenericEmailService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TicketMetaDataRepository ticketMetaDataRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private OrganizationsRepository organizationsRepository;

	@Autowired
	private DepartmentsRepository departmentsRepository;

	public void sendAMailOnTicketMadeAProgress(Long ticketid, Long from, Long to) throws Exception {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {

					TicketDTO ticketDTO = ticketRepository.getLatestTicket(ticketid);
					List<TicketMetaDataDTO> ticketMetaDataDTOs = ticketMetaDataRepository.findByTicketid(ticketid);
					List<UserDetailsDTO> userDetailsDTOls = userRepository.findByUserid(from);
					UserDetailsDTO assigneeUserDetails = userDetailsDTOls.get(0);
					List<UserDetailsDTO> userDetailsDTOlsa = userRepository.findByUserid(to);
					UserDetailsDTO assignedUserDetails = userDetailsDTOlsa.get(0);
					List<UserDetailsDTO> customerLs = userRepository
							.findByUserid(ticketMetaDataDTOs.get(0).getCreatoruserid());
					UserDetailsDTO customerUserDetails = customerLs.get(0);
					Optional<DepartmentsDTO> departmentsDTO = departmentsRepository
							.findById(ticketMetaDataDTOs.get(0).getDepid());
					DepartmentsDTO ddto = departmentsDTO.get();
					Optional<OrganizationsDTO> orgDTO = organizationsRepository
							.findById(ticketMetaDataDTOs.get(0).getOrgid());
					OrganizationsDTO odto = orgDTO.get();

					if (!ObjectUtils.isEmpty(ticketMetaDataDTOs) && ticketMetaDataDTOs.size() > 0
							&& !ObjectUtils.isEmpty(ticketDTO)) {
						TicketMetaDataDTO ticketMetaDataDTO = ticketMetaDataDTOs.get(0);
						sendMailToCustomer(ticketMetaDataDTO, ticketDTO, assigneeUserDetails, assignedUserDetails,
								customerUserDetails, ddto, odto);
						sendMailToAssignee(ticketMetaDataDTO, ticketDTO, assigneeUserDetails, assignedUserDetails,
								customerUserDetails, ddto, odto);
						sendMailToManagers(ticketMetaDataDTO, ticketDTO, assigneeUserDetails, assignedUserDetails,
								customerUserDetails, ddto, odto);

					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.print("*************ERRORR*************\n");
					e.printStackTrace();
				}
			}
		});
		t1.start();

	}

	private void sendMailToCustomer(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO assigneeUserDetails, UserDetailsDTO assignedUserDetails, UserDetailsDTO customerUserDetails,
			DepartmentsDTO ddto, OrganizationsDTO odto) throws Exception {
		String htmlContentForCustomer = returnCustomerBaseTicketTemplate(ticketMetaDataDTO, ticketDTO,
				assigneeUserDetails, assignedUserDetails);
		String subject = String.format("#Ticket %s raised by you  had a progress : %s, %s", ticketDTO.getTicketid(),
				ddto.getDepname(), odto.getOrgname());
		emailService.sendSimpleMessageWithHTML(customerUserDetails.getEmailid(), subject, htmlContentForCustomer);
	}

	private String returnCustomerBaseTicketTemplate(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO assigneeUserDetails, UserDetailsDTO assignedUserDetails) {
		String htmlContent = String.format(
				"<html><body><div style = 'justify-content:center;'><h2>%s</h2><br /><p>%s</p><br /><div>Ref Id: #Ticket %d</div><br /> Assigned to %s by %s<br /><br />  Thanks, <br /> TicketManagementSystem</div></body></html>",
				ticketDTO.getTitle(), ticketDTO.getContent(), ticketDTO.getTicketid(),
				assignedUserDetails.getUsername(), assigneeUserDetails.getUsername());
		return htmlContent;

	}

	private void sendMailToAssignee(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO assigneeUserDetails, UserDetailsDTO assignedUserDetails, UserDetailsDTO customerUserDetails,
			DepartmentsDTO ddto, OrganizationsDTO odto) throws Exception {
		String htmlContentForCustomer = returnAssigneedBaseTicketTemplate(ticketMetaDataDTO, ticketDTO,
				assigneeUserDetails, assignedUserDetails);
		String subject = String.format("#Ticket %s has been Assigned to you :  %s , %s", ticketDTO.getTicketid(),
				ddto.getDepname(), odto.getOrgname());
		emailService.sendSimpleMessageWithHTML(assignedUserDetails.getEmailid(), subject, htmlContentForCustomer);
	}

	private String returnAssigneedBaseTicketTemplate(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO assigneeUserDetails, UserDetailsDTO assignedUserDetails) {
		String workdone = ticketDTO.getWorkdone();
		if (workdone == null) {
			workdone = ": ";
		}
		String htmlContent = String.format(
				"<html><body><div style = 'justify-content:center;'><h2>%s</h2><br /><p>%s</p><br /><div>Ref Id: #Ticket %d</div><br /> Assigned to you by %s <br />  Workdone by the previous user %s<br /><br />  Thanks, <br /> TicketManagementSystem</div></body></html>",
				ticketDTO.getTitle(), ticketDTO.getContent(), ticketDTO.getTicketid(),
				assigneeUserDetails.getUsername(), workdone);
		return htmlContent;

	}

	private void sendMailToManagers(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO assigneeUserDetails, UserDetailsDTO assignedUserDetails, UserDetailsDTO customerUserDetails,
			DepartmentsDTO ddto, OrganizationsDTO odto) throws Exception {
		String htmlContentForCustomer = returnCustomerBaseTicketTemplate(ticketMetaDataDTO, ticketDTO,
				assigneeUserDetails, assignedUserDetails);
		String subject = String.format("#Ticket %s  in your organization has a progress : %s, %s ",
				ticketDTO.getTicketid(), ddto.getDepname(), odto.getOrgname());
		List<Map<String, Object>> emailList = userRepository
				.getAllManagersEmailIdForAnDepAndOrg(ticketMetaDataDTO.getDepid(), ticketMetaDataDTO.getOrgid());
		String[] to = new String[emailList.size()];
		for (int i = 0; i < emailList.size(); i++) {
			to[i] = (emailList.get(i).get("emailid").toString());
		}
		emailService.sendSimpleMessageWithHTMLToManypersons(to, subject, htmlContentForCustomer);
	}

	public void sendAMailOnTicketClose(Long ticketid, Long closedByUserId) throws Exception {

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					List<TicketDTO> ticketDTOs = ticketRepository.findByTicketid(ticketid);
					List<TicketMetaDataDTO> ticketMetaDataDTOs = ticketMetaDataRepository.findByTicketid(ticketid);
					List<UserDetailsDTO> userDetailsDTOls = userRepository.findByUserid(closedByUserId);
					UserDetailsDTO closedByUserIdUserDetails = userDetailsDTOls.get(0);
					List<UserDetailsDTO> customerLs = userRepository
							.findByUserid(ticketMetaDataDTOs.get(0).getCreatoruserid());
					UserDetailsDTO customerUserDetails = customerLs.get(0);
					Optional<DepartmentsDTO> departmentsDTO = departmentsRepository
							.findById(ticketMetaDataDTOs.get(0).getDepid());
					DepartmentsDTO ddto = departmentsDTO.get();
					Optional<OrganizationsDTO> orgDTO = organizationsRepository
							.findById(ticketMetaDataDTOs.get(0).getOrgid());
					OrganizationsDTO odto = orgDTO.get();
					if (!ObjectUtils.isEmpty(ticketMetaDataDTOs) && ticketMetaDataDTOs.size() > 0
							&& !ObjectUtils.isEmpty(ticketDTOs) && ticketDTOs.size() > 0) {
						TicketMetaDataDTO ticketMetaDataDTO = ticketMetaDataDTOs.get(0);
						TicketDTO ticketDTO = ticketDTOs.get(0);
						sendMailToCustomerForClosing(ticketMetaDataDTO, ticketDTO, closedByUserIdUserDetails,
								customerUserDetails, ddto, odto);
						sendMailToManagersForClosing(ticketMetaDataDTO, ticketDTO, closedByUserIdUserDetails,
								customerUserDetails, ddto, odto);

					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.print("*************ERRORR*************\n");
					e.printStackTrace();
				}
			}

		});
		t1.start();

	}

	private void sendMailToCustomerForClosing(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO closedByUserIdUserDetails, UserDetailsDTO customerUserDetails, DepartmentsDTO ddto,
			OrganizationsDTO odto) throws Exception {
		String htmlContentForCustomer = returnCustomerBaseTicketTemplateForClosing(ticketMetaDataDTO, ticketDTO,
				closedByUserIdUserDetails);
		String subject = String.format("#Ticket %s  had  closed: %s, %s ", ticketDTO.getTicketid(), ddto.getDepname(),
				odto.getOrgname());
		emailService.sendSimpleMessageWithHTML(customerUserDetails.getEmailid(), subject, htmlContentForCustomer);
	}

	private String returnCustomerBaseTicketTemplateForClosing(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO closedByUserIdUserDetails) {
		String htmlContent = String.format(
				"<html><body><div style = 'justify-content:center;'><h2>%s</h2><br /><p>%s</p><br /><div>Ref Id: #Ticket %d</div><br />Has been closed by   %s<br /><br />  Thanks, <br /> TicketManagementSystem</div></body></html>",
				ticketDTO.getTitle(), ticketDTO.getContent(), ticketDTO.getTicketid(),
				closedByUserIdUserDetails.getUsername());
		return htmlContent;

	}

	private void sendMailToManagersForClosing(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO closedByUserIdUserDetails, UserDetailsDTO customerUserDetails, DepartmentsDTO ddto,
			OrganizationsDTO odto) throws Exception {
		String htmlContentForCustomer = returnCustomerBaseTicketTemplateForClosing(ticketMetaDataDTO, ticketDTO,
				closedByUserIdUserDetails);
		String subject = String.format("#Ticket %s  had been closed : %s, %s", ticketDTO.getTicketid(),

				ddto.getDepname(), odto.getOrgname());
		List<Map<String, Object>> emailList = userRepository
				.getAllManagersEmailIdForAnDepAndOrg(ticketMetaDataDTO.getDepid(), ticketMetaDataDTO.getOrgid());
		String[] to = new String[emailList.size()];
		for (int i = 0; i < emailList.size(); i++) {
			to[i] = (emailList.get(i).get("emailid").toString());
		}
		emailService.sendSimpleMessageWithHTMLToManypersons(to, subject, htmlContentForCustomer);
	}

	private String returManagersBaseTicketTemplateForClosing(TicketMetaDataDTO ticketMetaDataDTO, TicketDTO ticketDTO,
			UserDetailsDTO closedByUserIdUserDetails) {
		String htmlContent = String.format(
				"<html><body><div style = 'justify-content:center;'><h2>%s</h2><br /><p>%s</p><br /><div>Ref Id: #Ticket %d</div><br />Has been closed by   %s<br /><br />  Thanks, <br /> TicketManagementSystem</div></body></html>",
				ticketDTO.getTitle(), ticketDTO.getContent(), ticketDTO.getTicketid(),
				closedByUserIdUserDetails.getUsername());
		return htmlContent;

	}
}