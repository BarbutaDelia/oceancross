import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-cruise',
  templateUrl: './add-cruise.component.html',
  styleUrls: ['./add-cruise.component.css']
})
export class AddCruiseComponent implements OnInit {

  constructor(private formBuilder: FormBuilder) { }
  public startDateSprint: Date = new Date();
  public addCruiseForm: FormGroup;

  
  ngOnInit(): void {
    this.addCruiseForm = this.formBuilder.group({
    name: new FormControl({ value: null, disabled: false }),
    price: new FormControl({ value: null, disabled: false }),
    startDate: new FormControl( { value: null, disabled: false }, this.changeMinDate.bind(this)),
    endDate: new FormControl({ value: null, disabled: false })
  });
  
  }

  public changeMinDate(control: FormControl) {
    this.startDateSprint = new Date(control.value);
  }

  public onSubmit()
  {
    console.log(this.addCruiseForm.controls.name.value);
    console.log(this.addCruiseForm.controls.price.value);
    console.log(this.addCruiseForm.controls.startDate.value);
    console.log(this.addCruiseForm.controls.endDate.value);
  }
  
}
