import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

import {Subject} from 'rxjs';
import {debounceTime, distinctUntilChanged, filter} from 'rxjs/operators';

@Component({
  selector: 'us-article-search',
  templateUrl: './article-search.component.html',
  styleUrls: ['./article-search.component.css']
})
export class ArticleSearchComponent implements OnInit {

  @Output()
  searchTerm = new EventEmitter<string>();

  value$ = new Subject<string>();

  constructor() { }

  ngOnInit(): void {
    this.value$.pipe(
      filter(value => value.length >= 3 || value.length === 0),
      debounceTime(500),
      distinctUntilChanged()
    ).subscribe(value => this.searchTerm.emit(value));
  }

  onInputChange(value: string): void {
    this.value$.next(value);
  }
}
