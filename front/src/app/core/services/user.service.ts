import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { NotificationFromServer } from 'src/app/shared/models/Notification';
import { AgentRequest } from 'src/app/shared/models/AgentRequest';
import { UserPrivilegeRequest } from 'src/app/shared/models/user/UserPrivilegeRequest';
import { User } from 'src/app/shared/models/user/User';
import { LoginRequestDTO } from 'src/app/shared/models/user/LoginRequestDTO';
import { UserDetails } from 'src/app/shared/models/user/UserDetails';
import { UserPrivilegesDTO } from 'src/app/shared/models/user/UserPrivilegesDTO';
const httpOptions = {headers: new HttpHeaders({'Content-Type' : 'application/json'})};

@Injectable({
  providedIn: 'root'
})
export class UserService {
  privilegeRequest: UserPrivilegeRequest = new UserPrivilegeRequest();
  constructor(private http: HttpClient) { }

  getUser(id: number) {
    return this.http.get<User>('server/user/'+id, httpOptions);
  }

  getAllAgentRequests() {
    return this.http.get<AgentRequest[]>('server/agentRequest', httpOptions);
  }

  getUsername(id : number) {
    return this.http.get<LoginRequestDTO>('server/user/username/'+id,  httpOptions);
  }

  canUserCreate(userId : number) {
    return this.http.get<boolean>('server/user/canUserCreate/'+userId,  httpOptions);
  }

  updateUserVehicleNum(userId : number) {
    return this.http.put<NotificationFromServer>('server/user/updateUserVehicleNumAfterCreate/'+userId,  httpOptions);
  }

  deleteUser(userId: number) {
    return this.http.delete<NotificationFromServer>('server/user/'+userId,  httpOptions);
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

  rejectAgent(agentRequest: any) {
    return this.http.delete('server/agentRequest/'+agentRequest.id, httpOptions);
  }

  approveAgent(agentRequest: any) {
    return this.http.put('server/agentRequest/'+agentRequest.id, httpOptions);
  }
}
