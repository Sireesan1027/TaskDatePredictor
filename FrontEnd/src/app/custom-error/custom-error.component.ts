import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-custom-error',
  templateUrl: './custom-error.component.html',
  styleUrls: ['./custom-error.component.scss'],
})
export class CustomErrorComponent {
  constructor() {}

  @Input() formGroup: FormGroup | null = null;
  @Input() name!: string;

  get property() {
    return this.formGroup?.get(this.name);
  }
}
