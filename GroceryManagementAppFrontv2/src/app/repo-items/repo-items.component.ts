import { Component, OnInit } from '@angular/core';
import { RepoItem } from '../repo-item';
import { RepoItemsService } from '../repo-items.service';

@Component({
  selector: 'app-repo-items',
  templateUrl: './repo-items.component.html',
  styleUrls: ['./repo-items.component.css']
})
export class RepoItemsComponent implements OnInit {

  repoItems: RepoItem[];

  constructor(private repoItemsService: RepoItemsService) {
  }

  ngOnInit(): void {
    this.listAllRepoItems();
  }

  listAllRepoItems(): void {
    this.repoItemsService.listAllRepoItems().subscribe(repoItemsResponse => this.repoItems = repoItemsResponse);
  }

}
