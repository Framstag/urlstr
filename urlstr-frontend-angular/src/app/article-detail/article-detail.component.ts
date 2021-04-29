import {Component, Input, OnInit} from '@angular/core';

import {Article} from '../shared/article';
import {Router} from '@angular/router';

@Component({
  selector: 'us-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.css']
})
export class ArticleDetailComponent implements OnInit {

  @Input()
  article: Article | null = null;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }
}
