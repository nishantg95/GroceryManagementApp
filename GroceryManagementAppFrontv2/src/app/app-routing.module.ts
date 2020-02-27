import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { ItemsComponent } from './items/items.component';
import { RepoItemsComponent } from './repo-items/repo-items.component';
import { RepoFormComponent } from './repo-form/repo-form.component';


const routes: Routes = [
  {
    path: 'GroceryManagementApp',
    children: [
      { path: '', component: DashboardComponent },
      { path: 'inventory', component: ItemsComponent },
      { path: 'repo',
      children: [
        { path: 'viewRepoItems', component: RepoItemsComponent},
        { path: 'addRepoItemForm', component: RepoFormComponent}
      ] },
    ]
  },
  { path: '', redirectTo: 'GroceryManagementApp', pathMatch: 'full' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
