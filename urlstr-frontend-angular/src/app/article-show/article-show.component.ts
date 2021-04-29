import { Component, OnInit } from '@angular/core';

import {ActivatedRoute, ParamMap, Router} from '@angular/router';

import {Observable} from 'rxjs';
import {switchMap} from 'rxjs/operators';

import {Article} from '../shared/article';
import {ArticleStoreService} from '../shared/article-store.service';

@Component({
  selector: 'us-article-show',
  templateUrl: './article-show.component.html',
  styleUrls: ['./article-show.component.css']
})
export class ArticleShowComponent implements OnInit {

  article$: Observable<Article> | undefined;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private articleStore: ArticleStoreService) { }

  ngOnInit(): void {
    this.article$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.articleStore.get(params.get('id') as string))
    );
  }
}
