import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticlesOverviewComponent } from './articles-overview.component';

describe('ArticleOverviewComponent', () => {
  let component: ArticlesOverviewComponent;
  let fixture: ComponentFixture<ArticlesOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ArticlesOverviewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticlesOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
