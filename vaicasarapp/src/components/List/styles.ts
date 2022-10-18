import styled from 'styled-components/native';

interface Props {
  check: boolean;
}

export const Container = styled.View`
  flex: 1;

  align-items: center;
  margin-top: 20px;
`;

export const Button = styled.TouchableOpacity`
  background-color: ${({ theme }) => theme.colors.green};

  width: 48px;
  height: 48px;

  border-radius: 50px;

  align-items: center;
  justify-content: center;
`;

export const Item = styled.TouchableOpacity<Props>`
  width: 300px;
  height: 50px;

  align-items: center;
  flex-direction: row;
  justify-content: space-between;

  border-width: 1px;
  border-color: ${({ theme, check }) =>
    check ? theme.colors.green : theme.colors.black};
  border-style: solid;

  margin: 10px;

  border-radius: 6px;
`;

export const Title = styled.Text`
  font-size: 16px;
  color: ${({ theme }) => theme.colors.gray};
  font-weight: 400;

  flex-grow: 1;
`;

export const DateText = styled.Text`
  font-size: 16px;
  color: ${({ theme }) => theme.colors.gray};
  margin-left: 5px;
`;

export const Checkbox = styled.TouchableOpacity<Props>`
  width: 28px;
  height: 28px;

  align-items: center;
  justify-content: center;

  border-width: 1px;
  border-color: ${({ theme, check }) =>
    check ? theme.colors.green : theme.colors.black};
  border-style: solid;

  margin-right: 10px;
  margin-left: 10px;
`;

export const IconButton = styled.TouchableOpacity``;
