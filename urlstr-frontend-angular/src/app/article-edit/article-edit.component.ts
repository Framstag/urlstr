import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Article} from '../shared/article';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {ArticleStoreService} from '../shared/article-store.service';
import {switchMap} from 'rxjs/operators';

@Component({
  selector: 'us-article-edit',
  templateUrl: './article-edit.component.html',
  styleUrls: ['./article-edit.component.css']
})
export class ArticleEditComponent implements OnInit {

  // @ts-ignore
  article$: Observable<Article>;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private articleStore: ArticleStoreService) { }

  ngOnInit(): void {
    this.article$ = this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>
        this.articleStore.get(params.get('id') as string))
    );
  }

  updateArticle(article: Article): void {
    console.log(`Updating article to: '${JSON.stringify(article)}'`);
    this.router.navigate(['/articles', article.id]);
  }
}
