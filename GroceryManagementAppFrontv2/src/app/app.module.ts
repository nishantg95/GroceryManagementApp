import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { NgbTypeaheadModule} from '@ng-bootstrap/ng-bootstrap';



import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ItemsComponent } from './items/items.component';
import { RepoItemsComponent } from './repo-items/repo-items.component';
import { LoadingInterceptor } from './interceptors/loading-interceptor';
import { ItemFormComponent } from './item-form/item-form.component';
import { RepoFormComponent } from './repo-form/repo-form.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ItemsComponent,
    RepoItemsComponent,
    ItemFormComponent,
    RepoFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgbTypeaheadModule
    // MatProgressSpinner
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
