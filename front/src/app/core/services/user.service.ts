import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { LoginRequestDTO } from 'src/app/shared/models/user/LoginRequestDTO';
import { NotificationFromServer } from 'src/app/shared/models/Notification';
import { User } from 'src/app/shared/models/user/User';
import { UserDetails } from 'src/app/shared/models/user/UserDetails';
import { UserPrivilegesDTO } from 'src/app/shared/models/user/UserPrivilegesDTO';
import { UserPrivilegeRequest } from 'src/app/shared/models/user/UserPrivilegeRequest';

const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  privilegeRequest: UserPrivilegeRequest = new UserPrivilegeRequest();
  constructor(private http: HttpClient) { }

  getUsername(id : number) {
    return this.http.get<LoginRequestDTO>('server/user/username/'+id,  httpOptions);
  }

  canUserCreate(userId : number) {
    return this.http.get<boolean>('server/user/canUserCreate/'+userId,  httpOptions);
  }

  updateUserVehicleNum(userId : number) {
    return this.http.put<NotificationFromServer>('server/user/updateUserVehicleNumAfterCreate/'+userId,  httpOptions);
  }

  getAllUsers() {
    return this.http.get<User[]>('server/user',  httpOptions);
  }

  getUserDetails(id: number) {
    return this.http.get<UserDetails>('server/userDetails/'+id,  httpOptions);
  }
  
  getUserPermissions(id: number) {
    return this.http.get<UserPrivilegesDTO>('server/userPrivilege/'+id,  httpOptions);
  }
  
  deletePermission(id: number, permission: string) {
    return this.http.delete<NotificationFromServer>('server/userPrivilege/'+id+'/'+permission, httpOptions);
  }

  postPermission(id: number, permission: string) {
    this.privilegeRequest.userPrivilege = permission;
    return this.http.post<NotificationFromServer>('server/userPrivilege/'+id, JSON.stringify(this.privilegeRequest), httpOptions);
  }
}
