import { Component, OnInit, Input } from '@angular/core';

import {Article} from '../shared/article';
import {Router} from '@angular/router';

@Component({
  selector: 'us-article-list-item',
  templateUrl: './article-list-item.component.html',
  styleUrls: ['./article-list-item.component.css']
})
export class ArticleListItemComponent {
  @Input() article!: Article;

  constructor(private router: Router) {}

  onClick(article: Article): void {
    // TODO: Move to routerLink in HTML
    this.router.navigate(['/articles', `${article.id}`]);
  }
}
