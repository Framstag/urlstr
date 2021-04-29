import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TagOverviewComponent } from './tag-overview.component';

describe('TagOverviewComponent', () => {
  let component: TagOverviewComponent;
  let fixture: ComponentFixture<TagOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TagOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TagOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
