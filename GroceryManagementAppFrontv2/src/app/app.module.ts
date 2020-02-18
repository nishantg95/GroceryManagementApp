import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { MatProgressSpinner } from '@angular/material/progress-spinner';
import { ReactiveFormsModule } from '@angular/forms';



import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ItemsComponent } from './items/items.component';
import { RepoItemsComponent } from './repo-items/repo-items.component';
import { LoadingInterceptor } from './interceptors/loading-interceptor';
import { ItemFormComponent } from './item-form/item-form.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    ItemsComponent,
    RepoItemsComponent,
    ItemFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    // MatProgressSpinner
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
