import { TestBed } from '@angular/core/testing';

import { VyapaarServiceService } from './vyapaar-service.service';

describe('VyapaarServiceService', () => {
  let service: VyapaarServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VyapaarServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
