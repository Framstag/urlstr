import { TestBed } from '@angular/core/testing';

import { TagStoreService } from './tag-store.service';

describe('TagService', () => {
  let service: TagStoreService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TagStoreService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
