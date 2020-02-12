import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Item } from './item';

@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  items: Observable<Item[]>;

  private BASE_URL = 'http://localhost:8080/GroceryManagementApp/data';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
     'Access-Control-Allow-Headers': 'Content-Type, Authorization',
     'Access-Control-Allow-Origin':  'http://localhost:4200',
     'Access-Control-Allow-Credentials': 'true' })
  };

  constructor(
    private http: HttpClient
  ) { }

  listAllItems(): Observable<Item[]> {
    this.items = this.http.get<Item[]>(this.BASE_URL + '/listAllItems')
      .pipe(catchError(this.handleError<Item[]>('listAllItems', [])));
    console.log(this.items);
    return this.items;
  }

/**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T>(operation = 'operation', result?: T) {
  return (error: any): Observable<T> => {

    // TODO: send the error to remote logging infrastructure
    console.error(error); // log to console instead

    // TODO: better job of transforming error for user consumption
    // this.log(`${operation} failed: ${error.message}`);

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

}
