export const environment = {
  production: true,

  basePath:'http://localhost:8080/',
  api_routes:{
    login:'ticketHandling/user/login',
    register:'ticketHandling/user/register',
    checkSession:'ticketHandling/user/checksession',
    getDepOrgMapping:'ticketHandling/data/getDepOrgMapping',
    getRoleInfo:'ticketHandling/data/getRoleInfo',
    createTicket:'ticketHandling/ticket/createTicket',
    moveTicketOpenToProgress:'ticketHandling/ticket/moveTicketOpenToProgress',
    getTagsList:'ticketHandling/data/getTagsList',
    getTheTicketsPieChartData:'ticketHandling/ticket/getTheTicketsPieChartData',//?orgid=2&depid=4
    getManagersAssociatedtoOrgDep:'ticketHandling/manager/getManagersAssociatedtoOrgDep',//?orgid=2&depid=4
    getTicketsDisplayInfo:'ticketHandling/ticket/getTicketsDisplayInfo',//?orgid=20&depid=4&status=0&pageNumber=1& =2
    getDepOrgMappingOnUserId:'ticketHandling/manager/getDepOrgMappingOnUserId'//?uid=7
  }
};
