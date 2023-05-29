import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { ApiService } from '../services/api.service';

@Component({
  selector: 'app-task-dialog',
  templateUrl: './task-dialog.component.html',
  styleUrls: ['./task-dialog.component.scss'],
})
export class TaskDialogComponent {
  taskForm = new FormGroup({
    taskName: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    startDate: new FormControl('', [Validators.required]),
    noOfDays: new FormControl('', [Validators.required]),
    endDate: new FormControl('', [Validators.required]),
  });

  minDate: Date = new Date();
  disabledDays: number[] = [0, 6];

  constructor(
    private apiService: ApiService,
    public dialogRef: DynamicDialogRef,
    public dialogConfig: DynamicDialogConfig
  ) {}

  onSubmit(e: any): void {
    console.log('e', this.taskForm.value);
    this.taskForm.patchValue({
      endDate: new Date(
        this.taskForm.get('endDate')?.value || ''
      ).toISOString(),
    });
    this.apiService.createTask(this.taskForm?.value, (isSuccess: boolean) => {
      this.dialogRef.close(isSuccess);
    });
  }

  dateUpdate = (r: any): void => {
    if (r) {
      this.taskForm.patchValue({
        endDate: r.endDate,
      });

      return;
    }
    this.taskForm.patchValue({
      endDate: null,
    });
  };

  omDateChange(e: string): void {
    this.taskForm.patchValue({
      endDate: null,
    });
    const date = new Date(e).toISOString();

    this.apiService.getEndDate(
      {
        startDate: date,
        noOfDays: parseInt(this.taskForm.get('noOfDays')?.value || '0'),
      },
      this.dateUpdate
    );
  }

  omDaysChange(e: any): void {
    this.taskForm.patchValue({
      endDate: null,
    });

    const endDate = this.taskForm.get('startDate')?.value;
    const date = new Date(endDate || '').toISOString();
    this.apiService.getEndDate(
      {
        startDate: date,
        noOfDays: parseInt(this.taskForm.get('noOfDays')?.value || '0'),
      },
      this.dateUpdate
    );
  }
}
