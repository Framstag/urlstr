import { TestBed } from '@angular/core/testing';

import { ArticleStoreService } from './article-store.service';
import {RouterTestingModule} from "@angular/router/testing";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('ArticleStoreService', () => {
  let service: ArticleStoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });
    service = TestBed.inject(ArticleStoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
