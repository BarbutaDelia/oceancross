import { ICruisePorts } from "./cruisePorts.interface";
import { IOnboardActivities } from "./onboardActivities.interface";

export interface ICruise{
    id: number,
    name: string,
    start_date: Date,
    end_date:Date,
    price: number,
    onboardActivities: IOnboardActivities[],
    cruisePorts: ICruisePorts[]
}