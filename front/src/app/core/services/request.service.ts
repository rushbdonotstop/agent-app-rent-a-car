import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'src/app/shared/models/user/User';
import { BundleDTO } from 'src/app/shared/models/request/bundleDTO';
import { RequestDTO } from 'src/app/shared/models/request/requestDTO';


const httpOptions = { headers: new HttpHeaders({ 'Content-Type': 'application/json' }) };

@Injectable({
    providedIn: 'root'
})
export class RequestService {

    user: User;

    constructor(private http: HttpClient) { }

    canUserPostReview(vehicleId: number, userId: number) {
        return this.http.get<boolean>('server/request/canUserPostReview/' + vehicleId + '+' + userId);
    }

    getOwnerRequestHistory() {

        this.user = JSON.parse(localStorage.getItem('userObject'));
        let ownerId = this.user.id;
        return this.http.get<Array<BundleDTO>>('server/request/ownerRequestHistory?ownerId=' + ownerId, httpOptions);
    }

    getBuyerRequestHistory() {

        this.user = JSON.parse(localStorage.getItem('userObject'));
        let userId = this.user.id;
        return this.http.get<Array<BundleDTO>>('server/request/buyerRequestHistory?userId=' + userId, httpOptions);
    }

    getOwnerSingleRequests() {
        this.user = JSON.parse(localStorage.getItem('userObject'));
        let ownerId = this.user.id;
        return this.http.get<Array<RequestDTO>>('server/request/ownerSingleRequests?ownerId=' + ownerId, httpOptions);
    }

   getBuyerSingleRequests() {
    this.user = JSON.parse(localStorage.getItem('userObject'));
    let userId = this.user.id;
    return this.http.get<Array<RequestDTO>>('server/request/buyerSingleRequests?userId=' + userId, httpOptions);
}


    changeStatusOfBundle(bundleId: number, changeType: number) {

        return this.http.get<boolean>('server/request/changeStatus?bundleId=' + bundleId + '&changeType=' + changeType, httpOptions);
    }

    changeStatusOfRequest(requestId: number, changeType: number) {
        return this.http.get<boolean>('server/request/changeSingleStatus?requestId=' + requestId + '&changeType=' + changeType, httpOptions);
    }

    finishedRequests() {
        this.user = JSON.parse(localStorage.getItem('userObject'));
        let ownerId = this.user.id;
        return this.http.get<Array<RequestDTO>>('server/request/rentingFinished?ownerId=' + ownerId, httpOptions);
    }
    finishedBundle() {
        this.user = JSON.parse(localStorage.getItem('userObject'));
        let ownerId = this.user.id;
        return this.http.get<Array<BundleDTO>>('server/request/rentingFinishedBundle?ownerId=' + ownerId, httpOptions);
    }

}