import {Injectable} from '@angular/core';

import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {Tag} from './tag';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TagStoreService {
  private api = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {}

  private static errorHandler(error: HttpErrorResponse): Observable<never> {
    console.error('Error: ');

    return throwError(error);
  }

  getAll(): Observable<Tag[]> {
    // TODO: Convert from (Raw)Tag from Backend to our Tag in Frontend
    return this.httpClient.get<Tag[]>(`${this.api}/tag/start`).pipe(
      catchError(TagStoreService.errorHandler)
    );
  }
}
