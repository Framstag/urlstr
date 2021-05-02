import {Component, Input, OnInit} from '@angular/core';

import { Article } from '../shared/article';
import { ArticleStoreService } from '../shared/article-store.service';

@Component({
  selector: 'us-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.css']
})
export class ArticleListComponent {
  @Input()
  articles: Article[] | null = null;

  constructor() { }
}
