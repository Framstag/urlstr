import { TestBed } from '@angular/core/testing';

import { TagStoreService } from './tag-store.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('TagService', () => {
  let service: TagStoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });
    service = TestBed.inject(TagStoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
