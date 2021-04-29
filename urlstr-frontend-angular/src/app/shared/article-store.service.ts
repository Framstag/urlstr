import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from '@angular/common/http';

import {Article} from './article';

@Injectable({
  providedIn: 'root'
})
export class ArticleStoreService {
  private api = 'http://localhost:8080';

  constructor(private httpClient: HttpClient) {
  }

  private static errorHandler(error: HttpErrorResponse): Observable<never> {
    console.error('Error: ');

    return throwError(error);
  }

  get(id: string): Observable<Article> {
    // TODO: Convert from (Raw)Article from Backend to our Article in Frontend
    return this.httpClient.get<Article>(`${this.api}/article/${id}`).pipe(
      catchError(ArticleStoreService.errorHandler)
    );
  }

  getAll(): Observable<Article[]> {
    // TODO: Convert from (Raw)Article from Backend to our Article in Frontend
    return this.httpClient.get<Article[]>(`${this.api}/article`).pipe(
      catchError(ArticleStoreService.errorHandler)
    );
  }

  getBySearch(searchTerm: string): Observable<Article[]> {
    const params = new HttpParams().set('search', searchTerm);

    // TODO: Convert from (Raw)Article from Backend to our Article in Frontend
    return this.httpClient.get<Article[]>(`${this.api}/article/search`,
      {params}).pipe(
      catchError(ArticleStoreService.errorHandler)
    );
  }

  update(article: Article): Observable<any> {
    // TODO: Convert from/to (Raw)Article from Backend to our Article in Frontend
    return this.httpClient.put(
      `${this.api}/articles/${article.id}`,
      article,
      {responseType: 'text'}
    ).pipe(
      catchError(ArticleStoreService.errorHandler)
    );
  }
}
