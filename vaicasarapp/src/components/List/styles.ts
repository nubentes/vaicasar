import styled from 'styled-components/native';

export const Item = styled.TouchableOpacity`
  width: 300px;
  height: 50px;

  align-items: center;
  flex-direction: row;
  justify-content: space-between;

  border: 1px solid black;

  margin: 5px;

  border-radius: 6px;
`;

export const Title = styled.Text`
  font-size: 16px;
  color: ${({ theme }) => theme.colors.gray};
  font-weight: 400;

  flex-grow: 1;
`;

export const Date = styled.Text`
  font-size: 16px;
  color: ${({ theme }) => theme.colors.gray};
  margin-left: 5px;
`;

export const Checkbox = styled.TouchableOpacity`
  width: 24px;
  height: 24px;

  align-items: center;
  justify-content: center;

  border-width: 1px;
  border-color: ${({ theme }) => theme.colors.gray};
  border-style: solid;

  margin-right: 10px;
  margin-left: 10px;
`;
