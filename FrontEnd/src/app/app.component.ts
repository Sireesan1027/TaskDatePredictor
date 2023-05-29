import { Component, OnDestroy, OnInit } from '@angular/core';
import { DialogService, DynamicDialogRef } from 'primeng/dynamicdialog';
import { ApiService } from './services/api.service';
import { TaskDialogComponent } from './task-dialog/task-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  providers: [DialogService],
})
export class AppComponent implements OnInit, OnDestroy {
  public tasks: any = null;
  ref!: DynamicDialogRef;

  constructor(
    private apiService: ApiService,
    public dialogService: DialogService
  ) {
    this.initTable();
  }

  initTable(): void {
    this.tasks = null;
    this.apiService.getAllTasks((r: any) => {
      if (r) {
        this.tasks = r;
        return;
      }
      this.tasks = null;
    });
  }

  ngOnInit(): void {}

  openTask(): void {
    this.ref = this.dialogService.open(TaskDialogComponent, {
      header: 'New Task',
      width: '70%',
      height: '85%',
      contentStyle: { overflow: 'hidden' },
      baseZIndex: 10000,
      maximizable: true,
    });

    this.ref.onClose.subscribe((data: any) => {
      if (typeof data === 'boolean') {
        // console.log('data', data);
        this.initTable();
      }

      this.ref.destroy();
    });
  }

  ngOnDestroy(): void {
    this.ref.destroy();
  }
}
