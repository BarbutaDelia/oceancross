import { Time } from "@angular/common";

export interface IPort {
    ID?: number,
    portName: String,
    arrivalDate:Date,
    arrivalTime: Time,
    duration: number
}