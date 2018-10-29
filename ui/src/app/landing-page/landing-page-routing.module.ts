import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { extract } from '@app/core';
import { Shell } from '@app/shell/shell.service';
import { LandingPageComponent } from './landing-page.component';

const routes: Routes = [
  Shell.childRoutes([{ path: 'landing-page', component: LandingPageComponent, data: { title: extract('Landing Page') } }])
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: []
})
export class LandingPageRoutingModule {}
