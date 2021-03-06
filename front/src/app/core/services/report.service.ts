import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Report } from 'src/app/shared/models/report/Report';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json' 
  })
}

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  constructor(private http: HttpClient) { }

  getAllFromVehicle(vehicleId: number) {
    return this.http.get<Report[]>('server/report/vehicle/'+vehicleId, httpOptions);
  }

  sendReport(report:Report){
    return this.http.post<Report>('server/report',report,httpOptions);
  }
}
