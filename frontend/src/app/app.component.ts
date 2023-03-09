import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { AuthenticateService } from './core/services/authenticate.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'pizza';
  url: string = "";
  constructor(
    private authService: AuthenticateService,
    private router: Router
  ) {
    this.getRoute();
  }
  submit(action: string) {
    switch (action) {
      case 'logout':
        this.authService.logout();
        break;
    
      default:
        break;
    }
  }
  getRoute() {
    this.router.events.subscribe((data) => {
      if (data instanceof NavigationEnd) {
        this.url = data.url;
      }
    });
  }
}
