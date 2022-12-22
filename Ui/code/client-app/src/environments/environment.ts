// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  basePath:'http://localhost:8080/',
  avatarsCount:16,
  api_routes:{
    login:'ticketHandling/user/login',
    logout:'ticketHandling/user/logout',
    updatePassword:'ticketHandling/user/updatePassword',
    updateAvatar:'ticketHandling/user/updateAvatar',
    register:'ticketHandling/user/register',
    checkSession:'ticketHandling/user/checksession',
    getDepOrgMapping:'ticketHandling/data/getDepOrgMapping',
    getRoleInfo:'ticketHandling/data/getRoleInfo',
    createTicket:'ticketHandling/ticket/createTicket',
    moveTicketOpenToProgress:'ticketHandling/ticket/moveTicketOpenToProgress',
    getTagsList:'ticketHandling/data/getTagsList',
    getTheTicketsPieChartData:'ticketHandling/ticket/getTheTicketsPieChartData',//?orgid=2&depid=4
    joinManagerInDepOrg:'ticketHandling/manager/joinManagerInDepOrg',
    getManagerEligibleJoiningDepOrg:'ticketHandling/manager/getManagerEligibleJoiningDepOrg',
    getManagersAssociatedtoOrgDep:'ticketHandling/manager/getManagersAssociatedtoOrgDep',//?orgid=2&depid=4
    getTicketsDisplayInfo:'ticketHandling/ticket/getTicketsDisplayInfo',//?orgid=20&depid=4&status=0&pageNumber=1& =2
    getDepOrgMappingOnUserId:'ticketHandling/manager/getDepOrgMappingOnUserId',//?uid=7
    getTicketViewInfo:'ticketHandling/ticket/getTicketViewInfo',
    getTicketEditInfo:'ticketHandling/ticket/getTicketEditInfo',
    bookMarkTicket:'ticketHandling/ticket/bookMarkTicket',
    getMyCustomerTickets:'ticketHandling/ticket/getMyCustomerTickets',
    searchCustomerTickets:'ticketHandling/ticket/searchCustomerTickets',
    getMyTickets:'ticketHandling/ticket/getMyTickets',
    searchTickets:'ticketHandling/ticket/searchTickets',
    getEligibleUserIdsListForTicketEdit:'ticketHandling/ticket/getEligibleUserIdsListForTicketEdit',
  },
  roleAccessLinks:{
    customer:{
      links:[
      {
        name:'Create Ticket',
<<<<<<< HEAD
        icon:'edit',
=======
        icon:'create_new_folder',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'create_ticket'
      },
      {
        name:'Ticket History',
<<<<<<< HEAD
        icon:'history',
=======
        icon:'dataset',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'ticket_history'
      }],
      default_link:'create_ticket'
    },
    orgmember:
    {
      links:[
      {
        name:'Manage Tickets',
<<<<<<< HEAD
        icon:'confirmation_number',
=======
        icon:'rebase_edit',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'manage_tickets'
      },
      {
        name:'Ticket History',
<<<<<<< HEAD
        icon:'history',
=======
        icon:'dataset',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'ticket_history'
      }],
      default_link:'manage_tickets'
    },
    manager:
    {
      links:[
      {
        name:'Join Organization',
<<<<<<< HEAD
        icon:'group_add',
=======
        icon:'add_task',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'join_org'
      },
      {
        name:'Add Org member',
<<<<<<< HEAD
        icon:'person_add',
=======
        icon:'library_add',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'add_org_mem'
      },
      {
        name:'Manage Tickets',
<<<<<<< HEAD
        icon:'confirmation_number',
=======
        icon:'rebase_edit',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'manage_tickets'
      },
      {
        name:'Ticket History',
<<<<<<< HEAD
        icon:'history',
=======
        icon:'dataset',
>>>>>>> 28545f8dc6f624784e154f3fd5838d3515518874
        id:'ticket_history'
      }],
      default_link:'join_org'
    }
  }
};

//http://localhost:4200/account/resetPassword?e=bhargav.gandham44@gmail.com&q=iRgfe4J9Zgfb8Yew9KeCXuSPeLtbQQtUAgegBEWeNKSiLSKfguu5AJ6ugaf5bZcKFaFWPCMVDiziu3ie7uZTRfhQWgzFYbe2fkcIg4QDAzDuk8bPTeDauAVeJSSWQ1sbZwef5Y1eeuLtD3M7gihYWZitRUNF94bCeXVtfhHzzFeeeWt1g7LeK2wki5Pt4zkizuu4ZhLK


/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
