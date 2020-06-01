import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PhoneCombination, PhoneService} from "./services/phone.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'phoneNumberUI';
  number: string="";
  page = 1;
  rowPerPage = 10;

  result: PhoneCombination;

  constructor(private phoneService: PhoneService) {}

  phoneNumberForm = new FormGroup({
    phoneNumber: new FormControl('', [Validators.required, Validators.minLength(7), Validators.maxLength(10)]),
  });

  getResult() {
    console.info(this.number);
    this.phoneService.getCombination(this.number, this.page, this.rowPerPage).subscribe(
      pc=>this.result = pc,error => this.result = null,
    );
  }

  get phoneNumber() { return this.phoneNumberForm.get('phoneNumber'); }

  prev() {
    if(this.result && this.page >= 2) {
      this.page -= 1;
      this.getResult();
    }
  }

  next() {
    if(this.result && this.page <= this.result.totalPage) {
      this.page += 1;
      this.getResult();
    }
  }
}
