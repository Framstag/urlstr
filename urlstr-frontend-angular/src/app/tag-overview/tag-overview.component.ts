import { Component, OnInit } from '@angular/core';
import {Tag} from '../shared/tag';
import {TagStoreService} from '../shared/tag-store.service';

@Component({
  selector: 'us-tag-overview',
  templateUrl: './tag-overview.component.html',
  styleUrls: ['./tag-overview.component.css']
})
export class TagOverviewComponent implements OnInit {
  tags: Tag[] = [];

  constructor(private tagStore: TagStoreService) { }

  ngOnInit(): void {
    this.tagStore.getAll().subscribe(tags => this.tags = tags);
  }
}
