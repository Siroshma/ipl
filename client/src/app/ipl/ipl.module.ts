import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { IplRoutingModule } from "./ipl-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import { TeamCreateComponent } from "./components/teamcreate/teamcreate.component";
import { CricketerCreateComponent } from "./components/cricketercreate/cricketercreate.component";
import { MatchCreateComponent } from "./components/matchcreate/matchcreate.component";
import { TicketBookingComponent } from "./components/ticketbooking/ticketbooking.component";
import { VoteComponent } from "./components/vote/vote.component";
import { RouterModule } from "@angular/router";

@NgModule({
  declarations: [
    TeamCreateComponent,
    CricketerCreateComponent,
    MatchCreateComponent,
    TicketBookingComponent,
    VoteComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
    IplRoutingModule,
    RouterModule
  ],
  exports: [
    
  ]
})
export class IplModule {}
