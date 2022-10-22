import { RFValue } from 'react-native-responsive-fontsize';
import styled from 'styled-components/native';

export const DataInput = styled.TextInput`
  width: ${RFValue(130)}px;
  height: ${RFValue(40)}px;

  border-radius: 6px;

  border-width: 1px;
  border-style: solid;
  border-color: ${({ theme }) => theme.colors.black};

  color: ${({ theme }) => theme.colors.black};

  padding-left: 10px;

  font-size: 16px;
`;
