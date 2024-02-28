import { Component, EventEmitter, Output } from '@angular/core';
import { BILL } from 'src/model/bill';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent {
  selected = '';
  selected2:any;
  searchedTitle:string =""

  billArray:BILL[]|undefined

 @Output()
 sendToContainer:EventEmitter<any>=new EventEmitter();
 searchinput(){
  this.sendToContainer.emit(this.searchedTitle);
 }
 @Output()
 sendToContainer1:EventEmitter<any>=new EventEmitter();
 searchinput1(){
  this.sendToContainer1.emit(this.selected);
 }
 @Output()
 sendToContainer2:EventEmitter<any>=new EventEmitter();
 searchinput2()
 {
   this.sendToContainer2.emit(this.selected2);
 }

 cleardata(){
  this.searchedTitle="";
  this.selected = "";
  this.selected2=
  this.sendToContainer1.emit(this.selected);
  this.sendToContainer.emit(this.searchedTitle);
  this.sendToContainer2.emit(this.selected2);
  }

}
