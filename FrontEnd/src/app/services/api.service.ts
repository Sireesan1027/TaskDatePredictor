import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Task } from '../types/Type';
@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl: string =
    'http://localhost:8085';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  getAllTasks(func: (res?: any) => void): void {
    this.http.get(`${this.baseUrl}/getAllTasks`).subscribe({
      next: (r: any) => {
        func(r);
      },
      error: (err: any) => {
        console.log('error', err);
        func();
      },
    });
  }

  getEndDate(
    body: { startDate: string; noOfDays: number },
    func: (result?: any) => void
  ): void {
    this.http.post(`${this.baseUrl}/endDatePrediction/create`, body).subscribe({
      next: (data: any) => {
        func(data);
      },
      error: (err: Error) => {
        console.log('err', Error);
        func();
      },
    });
  }

  createTask(body: Partial<Task>, func: (success: boolean) => void): void {
    this.http.post(`${this.baseUrl}/createTask`, body).subscribe({
      next: (r: any) => {
        this.messageService.add({
          severity: 'info',
          summary: 'Success',
          detail: 'Task sucessfully created',
          sticky: true,
        });
        func(true);
      },
      error: (err) => {
        this.messageService.add({
          severity: 'danger',
          summary: 'Error',
          detail: 'Cannot create task',
          sticky: true,
        });
        func(false);
      },
    });
  }
}
