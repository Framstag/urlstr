import { NgModule } from '@angular/core';

import { RouterModule, Routes } from '@angular/router';

import {ArticlesOverviewComponent} from './articles-overview/articles-overview.component';
import {ArticleShowComponent} from './article-show/article-show.component';
import {ArticleEditComponent} from './article-edit/article-edit.component';

import {TagOverviewComponent} from './tag-overview/tag-overview.component';

import {AboutComponent} from './about/about.component';

// TODO: Add a wildcard route at the end for non-existing routes
const routes: Routes = [
  {path: '', redirectTo: '/articles', pathMatch: 'full'},
  {path: 'articles/:id', component: ArticleShowComponent},
  {path: 'articles/:id/edit', component: ArticleEditComponent},
  {path: 'articles', component: ArticlesOverviewComponent},
  {path: 'tags', component: TagOverviewComponent},
  {path: 'about', component: AboutComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
