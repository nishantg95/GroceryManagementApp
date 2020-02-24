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
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
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
    alert('Lost connection to server');

    // Let the app keep running by returning an empty result.
    return of(result as T);
  };
}

deleteItem(item: Item): Observable<{}> {
  console.log(item.name);
  const response =  this.http.delete(this.BASE_URL + '/deleteItem/' + item.id, this.httpOptions).pipe(
    // tap(_ => this.log(`deleted item id=${item.id}`)),
    catchError(this.handleError<Item>('deleteItem'))
  );
  this.listAllItems();
  return response;
}


addItem(item: Item): Observable<{}> {
  const response =  this.http.post(this.BASE_URL + '/createItem/', item, this.httpOptions).pipe(
    // tap(_ => this.log(`deleted item id=${item.id}`)),
    catchError(this.handleError<Item>('addItem'))
  );
  this.listAllItems();
  return response;
}

}
