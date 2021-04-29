import {Component, EventEmitter, Input, OnChanges, OnInit, Output} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

import {Article} from '../shared/article';

@Component({
  selector: 'us-article-form',
  templateUrl: './article-form.component.html',
  styleUrls: ['./article-form.component.css']
})
export class ArticleFormComponent implements OnInit, OnChanges {
  // @ts-ignore
  form: FormGroup;

  @Input()
  article: Article | null = null;

  @Input()
  editing = false;

  @Output()
  submitArticle = new EventEmitter<Article>();

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initForm();
  }

  ngOnChanges(): void {
    this.initForm();
    if (this.article) {
      this.setFormValues(this.article);
    }
  }

  initForm(): void {
    if (this.form) {
      return;
    }

    this.form = this.fb.group({
      id: [{value: '', disabled: this.editing}, Validators.required],
      name: ['', Validators.required]
    });
  }

  setFormValues(article: Article): void {
    this.form.patchValue(article);
  }

  submitForm(article: Article): void {
    console.log('Form submitted:' + JSON.stringify(article));

    if (this.article) {
      const changedArticle: Article  = {
        ...article,
        id: this.article.id
      };
      this.submitArticle.emit(changedArticle);
    }
    else {
      this.submitArticle.emit(article);
    }

    this.form.reset();
  }
}
