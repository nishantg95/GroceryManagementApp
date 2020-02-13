import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

/** Pass untouched request through to the next request handler. */
@Injectable()
export class LoadingInterceptor implements HttpInterceptor {

  spinnerVisibility = false;

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      this.spinnerVisibility = true;
      console.log('hulllooooo');
      return next.handle(req).pipe(finalize(() => this.spinnerVisibility = false));
  }
}
