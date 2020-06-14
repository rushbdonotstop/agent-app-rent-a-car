import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { CatalogueItem } from 'src/app/shared/models/catalogue/CatalogueItem';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class CatalogueService {

  constructor(private http: HttpClient) { }

  getModelByMake(makeId : number) {
    return this.http.get<CatalogueItem[]>('server/catalogue/vehicleModel/byMake/'+makeId,  httpOptions);
  }

  getMake(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/vehicleMake/'+id,  httpOptions);
  }

  getModel(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/vehicleModel/'+id,  httpOptions);
  }

  getFuelType(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/vehicleFuelType/'+id,  httpOptions);
  }

  getStyle(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/vehicleStyle/'+id,  httpOptions);
  }

  getTransmission(id : number) {
    return this.http.get<CatalogueItem>('server/catalogue/vehicleTransmission/'+id,  httpOptions);
  }

  getMakes() {
    return this.http.get<CatalogueItem[]>('server/catalogue/vehicleMake/',  httpOptions);
  }

  getModels() {
    return this.http.get<CatalogueItem[]>('server/catalogue/vehicleModel/',  httpOptions);
  }

  getFuelTypes() {
    return this.http.get<CatalogueItem[]>('server/catalogue/vehicleFuelType/',  httpOptions);
  }

  getStyles() {
    return this.http.get<CatalogueItem[]>('server/catalogue/vehicleStyle/',  httpOptions);
  }

  getTransmissions() {
    return this.http.get<CatalogueItem[]>('server/catalogue/vehicleTransmission/',  httpOptions);
  }
}
