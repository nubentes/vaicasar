import React from 'react';
import { TextInputProps } from 'react-native';

import { DataInput } from './styles';

interface Props extends TextInputProps {
  value: string;
  onChangeText: any;
  editable: boolean;
}

export default function Input({ value, onChangeText, editable }: Props) {
  return (
    <DataInput
      value={value}
      autoCorrect={false}
      autoCapitalize={false}
      onChangeText={(text: string) => onChangeText(text)}
      editable={editable}
    />
  );
}
