import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpParams } from '@angular/common/http';
import { MinAndMaxPricesDTO } from 'src/app/shared/models/MinAndMaxPricesDTO';
import { Pricelist } from 'src/app/shared/models/pricelist/Pricelist';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class PricelistService {

  constructor(private http: HttpClient) { }

  getPricelists(id : number) {
    return this.http.get<Pricelist[]>('server/pricelist/'+id,  httpOptions);
  }

  minAndMax() {
    return this.http.get<MinAndMaxPricesDTO>('server/pricelist/minAndMax',  httpOptions);
  }

  validatePricelists(pricelists : Pricelist[], startDate : Date, endDate : Date) {
    let params = new HttpParams();

    params = params.append('startDate', startDate.toISOString())
    params = params.append('endDate', endDate.toISOString())

    const options = {
      headers : new HttpHeaders({'Content-Type' : 'application/json'}),
      params : params
    }

    return this.http.post<Pricelist[]>('server/pricelist/validatePricelists', pricelists, options);
  }

  savePricelists(pricelists : Pricelist[], startDate : Date, endDate : Date) {
    let params = new HttpParams();

    params = params.append('startDate', startDate.toISOString())
    params = params.append('endDate', endDate.toISOString())

    const options = {
      headers : new HttpHeaders({'Content-Type' : 'application/json'}),
      params : params
    }

    return this.http.put<Notification>('server/pricelist/', pricelists, options);
  }
}

export class Notification{
  text : String;
}
