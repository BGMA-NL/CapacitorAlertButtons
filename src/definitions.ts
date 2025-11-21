export interface AlertButtonsPlugin {
  showDialog(options: DialogOptions): Promise<{ value: string }>;
}

export interface DialogOptions {
  buttons: DialogButton[];
}

export interface DialogButton {
  text: string;
  role: 'cancel' | 'default';
}
