import { ICruisePorts } from "./cruisePorts.interface";
import { IOnboardActivities } from "./onboardActivities.interface";

export interface ICruiseRequest{
    id: number,
    name: string,
    start_date: string,
    end_date:string,
    price: number,
    onboardActivities: IOnboardActivities[],
    cruisePorts: ICruisePorts[]
}