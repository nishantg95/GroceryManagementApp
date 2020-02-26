import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { RepoItem } from './repo-item';

@Injectable({
  providedIn: 'root'
})
export class RepoItemsService {
  REST_REPO_URL = 'http://localhost:8080/GroceryManagementApp/repo';
  repoItems: Observable<RepoItem[]>;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
  }

  listAllRepoItems(): Observable<RepoItem[]> {
    this.repoItems = this.http
      .get<RepoItem[]>(this.REST_REPO_URL + '/listAllRepoItems')
      .pipe(catchError(this.handleError<RepoItem[]>('listAllRepoItems', [])));
    return this.repoItems;
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
      alert(error.status + error.message);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  addRepoItem(repoItem: RepoItem): Observable<{}> {
    const response = this.http
      .post(this.REST_REPO_URL + '/addRepoItem', repoItem, this.httpOptions)
      .pipe(
        // tap(_ => this.log(`deleted item id=${item.id}`)),
        catchError(this.handleError<RepoItem>('addRepoItem'))
      );
    this.listAllRepoItems();
    return response;
  }
}
