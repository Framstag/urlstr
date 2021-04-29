import { Component, OnInit } from '@angular/core';
import {Article} from '../shared/article';
import {ArticleStoreService} from '../shared/article-store.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'us-articles-overview',
  templateUrl: './articles-overview.component.html',
  styleUrls: ['./articles-overview.component.css']
})
export class ArticlesOverviewComponent implements OnInit {
  articles$: Observable<Article[]> | undefined;

  constructor(private articleStore: ArticleStoreService) { }

  ngOnInit(): void {
    this.articles$ = this.articleStore.getAll();
  }

  onSearchChange(value: string): void {
    this.articles$ = this.articleStore.getBySearch(value);
  }
}
