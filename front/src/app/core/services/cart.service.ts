import { Injectable } from '@angular/core';
import { Cart } from 'src/app/shared/models/cart/Cart';
import { VehicleMainViewDTO } from 'src/app/shared/models/vehicle/VehicleMainViewDTO';
import { RequestAndVehicle } from 'src/app/shared/models/cart/RequestAndVehicle';
import { DetailedCart } from 'src/app/shared/models/cart/DetailedCart';
import { BundleAndVehicle } from 'src/app/shared/models/cart/BundleAndVehicle';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UserService } from './user.service';
import { manualRequest } from 'src/app/shared/models/cart/manualRequest';

const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
  providedIn: 'root'
})
//TODO : get owner id
export class CartService {

  constructor(private http: HttpClient, private loginService: UserService) { }

  addItemToCart(vehicle: VehicleMainViewDTO, startDate: Date, endDate: Date) {
    var cart = localStorage.getItem('cart')
    if (cart == null) {
      var newCart = new DetailedCart()
      var request = new RequestAndVehicle(vehicle)
      request.startDate = startDate
      request.endDate = endDate
      request.ownerId = vehicle.ownerId
      newCart.requests.push(request)
      localStorage.setItem('cart', JSON.stringify(newCart))
      console.log(localStorage.getItem('cart'))
    }
    else {
      var oldCart = JSON.parse(localStorage.getItem('cart'));
      var request = new RequestAndVehicle(vehicle)
      request.startDate = startDate
      request.endDate = endDate
      request.ownerId = vehicle.ownerId
      oldCart.requests.push(request)
      localStorage.setItem('cart', JSON.stringify(oldCart))
      console.log(localStorage.getItem('cart'))
    }
  }

  addBundleToCart(bundleList: VehicleMainViewDTO[]) {
    var cart = localStorage.getItem('cart')
    console.log(bundleList)
    if (cart == null) {
      var newCart = new DetailedCart()
      var bundle = new BundleAndVehicle()
      bundle.id=null
      for (let b of bundleList) {
        var bun = new RequestAndVehicle(b)
        bun.ownerId = bundleList[0].ownerId
        bundle.requests.push(bun)
      }
      newCart.bundles.push(bundle)
      localStorage.setItem('cart', JSON.stringify(newCart))
      console.log(localStorage.getItem('cart'))
    }
    else {
      var oldCart = JSON.parse(localStorage.getItem('cart'));
      var bundle = new BundleAndVehicle()
      bundle.id=null
      for (let b of bundleList) {
        var bun = new RequestAndVehicle(b)
        bun.ownerId = bundleList[0].ownerId
        bundle.requests.push(bun)
      }
      oldCart.bundles.push(bundle)
      localStorage.setItem('cart', JSON.stringify(oldCart))
      console.log(localStorage.getItem('cart'))
    }
  }

  getCart() {
    return JSON.parse(localStorage.getItem('cart'));
  }

  updateRequests(requests: RequestAndVehicle[]) {
    var oldCart = JSON.parse(localStorage.getItem('cart'));
    oldCart.requests = requests
    localStorage.setItem('cart', JSON.stringify(oldCart))
  }

  updateBundles(bundles: BundleAndVehicle[]) {
    var oldCart = JSON.parse(localStorage.getItem('cart'));
    oldCart.bundles = bundles
    localStorage.setItem('cart', JSON.stringify(oldCart))
  }

  buy() {
    var detailedCart = this.getCart()
    var userId = JSON.parse(localStorage.getItem('userObject')).id;
    var cart = new Cart(detailedCart, userId)
    console.log(detailedCart)
    console.log(cart)
    return this.http.post<boolean>('server/request/request', JSON.stringify(cart), httpOptions);
  }

  newCart() {
    localStorage.setItem('cart', JSON.stringify(new DetailedCart()))
  }

  manualRent(request: manualRequest) {
    return this.http.post<boolean>('server/request/request/physicalRent', JSON.stringify(request), httpOptions);
  }

}
