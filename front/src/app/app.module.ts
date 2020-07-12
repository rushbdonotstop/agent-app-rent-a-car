import { BrowserModule, HAMMER_GESTURE_CONFIG } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { HomeModule } from './home/home.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { GestureConfig, MatTabsModule, MatTableModule } from '@angular/material';
import { MatButtonModule } from '@angular/material/button';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { FormsModule } from '@angular/forms';
import { MatListModule, MatListItem } from '@angular/material/list';
import { RentDialogComponent } from './components/rent-dialog/rent-dialog.component';
import { CartDialogComponent } from './components/cart-dialog/cart-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    RentDialogComponent,
    CartDialogComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CoreModule,
    HomeModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MatButtonModule,
    OwlDateTimeModule,
    OwlNativeDateTimeModule,
    FormsModule,
    MatListModule,
    MatTabsModule,
    MatTableModule
  ],
  providers: [{ provide: HAMMER_GESTURE_CONFIG, useClass: GestureConfig }],
  bootstrap: [AppComponent],
  exports: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [RentDialogComponent, CartDialogComponent]
})
export class AppModule { }
