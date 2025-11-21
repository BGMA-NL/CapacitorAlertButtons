import { WebPlugin } from '@capacitor/core';

import type { AlertButtonsPlugin } from './definitions';

export class AlertButtonsWeb extends WebPlugin implements AlertButtonsPlugin {
  async showDialog(): Promise<{ value: string }> {
    throw new Error('showDialog not implemented on web');
  }
}
