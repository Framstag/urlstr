import {Inject, NgModule} from '@angular/core';
import { LOCALE_ID } from '@angular/core';

import {registerLocaleData} from '@angular/common';
import localeDe from '@angular/common/locales/de';
import localeEn from '@angular/common/locales/es';

import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ReactiveFormsModule } from '@angular/forms';

import {HttpClientModule} from '@angular/common/http';

import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatToolbarModule} from '@angular/material/toolbar';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { ArticleListComponent } from './article-list/article-list.component';
import { ArticleListItemComponent } from './article-list-item/article-list-item.component';
import { ArticlesOverviewComponent } from './articles-overview/articles-overview.component';
import { AboutComponent } from './about/about.component';
import { TagOverviewComponent } from './tag-overview/tag-overview.component';
import { TagListComponent } from './tag-list/tag-list.component';
import { ArticleSearchComponent } from './article-search/article-search.component';
import { ArticleShowComponent } from './article-show/article-show.component';
import { ArticleDetailComponent } from './article-detail/article-detail.component';
import { ArticleEditComponent } from './article-edit/article-edit.component';
import { ArticleFormComponent } from './article-form/article-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ArticleListComponent,
    ArticleListItemComponent,
    ArticlesOverviewComponent,
    AboutComponent,
    TagOverviewComponent,
    TagListComponent,
    ArticleSearchComponent,
    ArticleShowComponent,
    ArticleDetailComponent,
    ArticleEditComponent,
    ArticleFormComponent
  ],
  imports: [
    AppRoutingModule,

    BrowserModule,
    BrowserAnimationsModule,

    ReactiveFormsModule,

    HttpClientModule,

    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatSidenavModule,
    MatTableModule,
    MatToolbarModule
  ],
  providers: [
    // { provide: LOCALE_ID, useValue: 'us' }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(@Inject(LOCALE_ID) locale: string) {
    // This is the implicit default
    registerLocaleData(localeDe);
    registerLocaleData(localeEn);

    console.log('Current Locale:', locale);
  }
}
